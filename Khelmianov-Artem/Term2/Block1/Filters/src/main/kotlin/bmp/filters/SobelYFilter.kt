package bmp.bmp.filters

import bmp.bmp.AKernelFilter

object SobelYFilter : AKernelFilter() {
    override var radius = 1
    override var kernel = listOf(
        -1f, 0f, 1f,
        -2f, 0f, 2f,
        -1f, 0f, 1f
    )
}