DESCRIPTION = "Berkeley ABC: System for Sequential Logic Synthesis and Formal Verification"
HOMEPAGE = "http://www.eecs.berkeley.edu/~alanmi/abc/"
# License is MIT, but differs from standard license text
LICENSE = "MIT"
SECTION = "devel/hardware"

LIC_FILES_CHKSUM = "file://copyright.txt;md5=04eb1ba7f36d87b164b6224e5417b860"

SRC_URI = "hg://bitbucket.org/alanmi/abc;protocol=https;branch=default;module=root"
SRCREV = "902018356af42d7653a3a5114ab0d2aaa28cc9f0"

S = "${WORKDIR}/root"

PV = "1.01+hg${SRCPV}"

DEPENDS = "readline"

# set the ARCHFLAGS to avoid execution of arch_flags during build
export ARCHFLAGS = "${ABC_ARCHFLAGS}"
ABC_ARCHFLAGS_x86-64 = "-DLIN64 -DSIZEOF_VOID_P=8 -DSIZEOF_LONG=8 -DSIZEOF_INT=4"
ABC_ARCHFLAGS = "-DLIN -DSIZEOF_VOID_P=4 -DSIZEOF_LONG=4 -DSIZEOF_INT=4 -fpermissive -w"

inherit cmake

do_install() {
	install -Dm 755 ${B}/abc ${D}${bindir}/abc
}

BBCLASSEXTEND = "native nativesdk"
