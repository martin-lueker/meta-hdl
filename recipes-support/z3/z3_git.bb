DESCRIPTION = "Z3 is a theorem prover from Microsoft Research."
HOMEPAGE = "https://github.com/Z3Prover/z3/wiki"
LICENSE = "MIT"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5f03ad1486a2e4ce71200ce0f9721557"
SRC_URI = "git://github.com/Z3Prover/z3;protocol=https"
SRCREV = "a51e40a6cd4f2f2897dbbbbac96ceda7fa07f0c1"

S = "${WORKDIR}/git"

PV = "4.8.8+git${SRCPV}"

inherit cmake python3native

BBCLASSEXTEND = "native nativesdk"
