# Copyright (C) 2013-2016 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

require imx-gpu-viv.inc

SRC_URI = "${FSL_MIRROR}/${PN}-${PV}.bin;fsl-eula=true"

S="${WORKDIR}/${PN}-${PV}"

SRC_URI[md5sum] = "6bc8c9ccde550f157b5772497cd423ca"
SRC_URI[sha256sum] = "c084e1d02db71588fbc0c61051ee08c532c7715d548739f86447840ad866e59d"

PACKAGE_FP_TYPE = "hardfp"

COMPATIBLE_MACHINE = "(mx6q|mx6dl|mx6sx|mx6sl)"