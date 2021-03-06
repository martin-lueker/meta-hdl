SUMMARY = "Small footprint and configurable Ethernet core"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=70ab32fd17078e7007b69f144468f821"

SRC_URI = "git://github.com/enjoy-digital/liteeth;protocol=https"
SRCREV = "792013a1756ea50608726ee86989ec38cfc35a8b"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "liteiclink"

BBCLASSEXTEND = "native nativesdk"
