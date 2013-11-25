# Copyright (C) 2013 Freescale Semiconductor

# Note this codec must be downloaded from freescale.com/imx
DESCRIPTION = "Freescale AAC+ decoder libraries "
SECTION = "multimedia"
LICENSE = "Proprietary"
LICENSE_FLAGS = "license_${PN}-${PV}"
LIC_FILES_CHKSUM = "file://EULA.txt;md5=93b784b1c11b3fffb1638498a8dde3f6"

DEPENDS = "libfslcodec"

inherit fsl-eula-unpack autotools pkgconfig

SRC_URI = "${FSL_MIRROR}/${PN}-${PV}.bin;fsl-eula=true"
S = "${WORKDIR}/${PN}-${PV}"

SRC_URI[md5sum] = "cf446c1eead06c666d42b1eade2ce6cc"
SRC_URI[sha256sum] = "3d5482a32567645c2db3917720efa095af8dbe48e078bd6a05227675cfb7dc57"

# Choose between Soft Float-Point and Hard Float-Point
EXTRA_OECONF = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', '--enable-fhw', '', d)}"

do_install_append() {
    # LTIB move the files around or gst-fsl-plugin won't find them
    mv $p ${D}${libdir}/imx-mm/audio-codec/*.so* ${D}${libdir}

}

# FIXME: All binaries lack GNU_HASH in elf binary but as we don't have
# the source we cannot fix it. Disable the insane check for now.
INSANE_SKIP_${PN} = "ldflags textrel"
INSANE_SKIP_${PN}-testapps = "ldflags"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PACKAGES += "${PN}-testapps"

FILES_${PN} += "${libdir}/imx-mm/audio-codec/wrap/*${SOLIBS}"
FILES_${PN}-dev += "${libdir}/pkgconfig/*.pc ${includedir}/imx-mm/* \
                    ${libdir}/imx-mm/audio-codec/wrap/*${SOLIBSDEV}"
FILES_${PN}-dbg += "${libdir}/imx-mm/audio-codec/.debug  ${libdir}/imx-mm/audio-codec/wrap/.debug"
# Add examples to -testapps PACKAGE
FILES_${PN}-testapps += "${datadir}/imx-mm/*"

COMPATIBLE_MACHINE = "(mx28|mx5|mx6)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
