DESCRIPTION = "Arachne Place and Route tool for iCE40 FPGAs"
HOMEPAGE = "https://github.com/cseed/arachne-pnr"
LICENSE = "MIT"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=3bb65c4bee625d497d43f50e6dacb4e2"

SRC_URI = "git://github.com/YosysHQ/arachne-pnr;protocol=https"
SRCREV = "840bdfdeb38809f9f6af4d89dd7b22959b176fdd"

PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

# needs coreutils for 'sum'
# icestorm is needed for chipdb
# depends on itself for processing chipdb content
DEPENDS = "coreutils-native icestorm-native arachne-pnr-native"

# export the path for icestorm chipdb
export ICEBOX = "${STAGING_DIR_NATIVE}${datadir_native}/icebox"

# set executable for use when processing chipdb
ARACHNEPNR_class-native = "${B}/bin/arachne-pnr"
ARACHNEPNR = "arachne-pnr"

EXE ?= ""
EXE_mingw32 = ".exe"
EXTRA_OEMAKE_append_mingw32 = " EXE=.exe"

do_compile () {
	# prevent the version string from containing the compiler, as this differs between host/target/nativesdk
	sed -i 's/, $(notdir $(CXX)) `$(CXX) --version.*` $(filter.*$(CXXFLAGS))//' ${S}/Makefile

	oe_runmake bin/arachne-pnr${EXE}

	# manually build the chipdb.bin files
	mkdir -p ${B}/share/arachne-pnr
	${ARACHNEPNR} -d 384 -c ${ICEBOX}/chipdb-384.txt --write-binary-chipdb ${B}/share/arachne-pnr/chipdb-384.bin
	${ARACHNEPNR} -d 1k -c ${ICEBOX}/chipdb-1k.txt --write-binary-chipdb ${B}/share/arachne-pnr/chipdb-1k.bin
	${ARACHNEPNR} -d 5k -c ${ICEBOX}/chipdb-5k.txt --write-binary-chipdb ${B}/share/arachne-pnr/chipdb-5k.bin
	${ARACHNEPNR} -d 8k -c ${ICEBOX}/chipdb-8k.txt --write-binary-chipdb ${B}/share/arachne-pnr/chipdb-8k.bin
}

do_install () {
	install -Dm 755 ${B}/bin/arachne-pnr${EXE} ${D}/${bindir}/arachne-pnr${EXE}
	install -Dm 644 ${B}/share/arachne-pnr/chipdb-384.bin ${D}/${datadir}/arachne-pnr/chipdb-384.bin
	install -Dm 644 ${B}/share/arachne-pnr/chipdb-1k.bin ${D}/${datadir}/arachne-pnr/chipdb-1k.bin
	install -Dm 644 ${B}/share/arachne-pnr/chipdb-5k.bin ${D}/${datadir}/arachne-pnr/chipdb-5k.bin
	install -Dm 644 ${B}/share/arachne-pnr/chipdb-8k.bin ${D}/${datadir}/arachne-pnr/chipdb-8k.bin
}

# populate the chipdb into the sysroot (not enabled by default on -native)
SYSROOT_DIRS_NATIVE_append = " ${datadir}/arachne-pnr"

BBCLASSEXTEND = "native nativesdk"
