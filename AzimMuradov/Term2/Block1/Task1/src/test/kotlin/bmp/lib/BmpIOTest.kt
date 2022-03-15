package bmp.lib

import bmp.TestUtils.TEST_RES_PATH
import bmp.lib.ValidatedBmp.Companion.validated
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class BmpIOTest {
    @Nested
    inner class ReadBmp {
        @Test
        fun `successfully read bmp`() {
            assertEquals(
                expected = Bmp(
                    fileHeader = BmpFileHeader(
                        fileType = 19778.toUShort(),
                        fileSize = 258u,
                        reserved1 = 0.toUShort(),
                        reserved2 = 0.toUShort(),
                        bitmapOffset = 138u
                    ),
                    infoHeader = BmpInfoHeader(
                        size = 124u,
                        width = 6,
                        height = 6,
                        planes = 1.toUShort(),
                        bitsPerPixel = 24.toUShort(),
                        compression = 0u,
                        sizeOfBitmap = 120u,
                        horzResolution = 2834,
                        vertResolution = 2834,
                        colorsUsed = 0u,
                        colorsImportant = 0u
                    ),
                    pixels = PIXELS
                ),
                actual = BmpIO.readBmp("${TEST_RES_PATH}bmp_24_6.bmp").bmp
            )
        }

        @Test
        fun `fail to read Bmp with short file size 1`() {
            assertFailsWith<IllegalStateException>(message = "Wrong BMP definition") {
                BmpIO.readBmp("${TEST_RES_PATH}bmp_24_6_error_1.bmp")
            }
        }

        @Test
        fun `fail to read Bmp with short file size 2`() {
            assertFailsWith<IllegalStateException>(message = "Wrong BMP definition") {
                BmpIO.readBmp("${TEST_RES_PATH}bmp_24_6_error_2.bmp")
            }
        }
    }

    @Nested
    inner class WriteBmp {
        private lateinit var tempFolder: File

        private val tempFilePath get() = tempFolder.resolve("out.bmp").absolutePath

        @BeforeEach
        fun setUp() {
            tempFolder = File(createTempDirectory("TEST").toUri())
        }

        @AfterEach
        fun tearDown() {
            tempFolder.deleteRecursively()
        }


        @Test
        fun `write 24 bit Bmp`() {
            BmpIO.writeBmp(path = tempFilePath, validatedBmp = BMP_TO_WRITE24)

            assertEquals(expected = BMP_TO_WRITE24, actual = BmpIO.readBmp(tempFilePath))
        }

        @Test
        fun `write 32 bit Bmp`() {
            BmpIO.writeBmp(path = tempFilePath, validatedBmp = BMP_TO_WRITE32)

            assertEquals(expected = BMP_TO_WRITE32, actual = BmpIO.readBmp(tempFilePath))
        }
    }


    private companion object {
        private val PIXELS = listOf(
            listOf(
                Color(0x05.toUByte(), 0x05.toUByte(), 0xF6.toUByte()),
                Color(0xFC.toUByte(), 0x62.toUByte(), 0x2A.toUByte()),
                Color(0x00.toUByte(), 0x00.toUByte(), 0x00.toUByte()),
                Color(0x05.toUByte(), 0xF6.toUByte(), 0xE5.toUByte()),
                Color(0xF7.toUByte(), 0x6A.toUByte(), 0x4A.toUByte()),
                Color(0x0E.toUByte(), 0xF6.toUByte(), 0x05.toUByte())
            ),
            listOf(
                Color(0xF9.toUByte(), 0x84.toUByte(), 0x36.toUByte()),
                Color(0xF8.toUByte(), 0x6F.toUByte(), 0x3C.toUByte()),
                Color(0x05.toUByte(), 0x05.toUByte(), 0xF6.toUByte()),
                Color(0x0E.toUByte(), 0xF6.toUByte(), 0x05.toUByte()),
                Color(0xF8.toUByte(), 0x62.toUByte(), 0x51.toUByte()),
                Color(0xEA.toUByte(), 0x62.toUByte(), 0x64.toUByte())
            ),
            listOf(
                Color(0x0E.toUByte(), 0xF6.toUByte(), 0x05.toUByte()),
                Color(0xF8.toUByte(), 0x7C.toUByte(), 0x33.toUByte()),
                Color(0xF7.toUByte(), 0x6E.toUByte(), 0x40.toUByte()),
                Color(0x05.toUByte(), 0xF6.toUByte(), 0xE5.toUByte()),
                Color(0x00.toUByte(), 0x00.toUByte(), 0x00.toUByte()),
                Color(0xF3.toUByte(), 0x62.toUByte(), 0x58.toUByte())
            ),
            listOf(
                Color(0xF6.toUByte(), 0x6E.toUByte(), 0x2A.toUByte()),
                Color(0xF7.toUByte(), 0x6D.toUByte(), 0x14.toUByte()),
                Color(0x05.toUByte(), 0x05.toUByte(), 0xF6.toUByte()),
                Color(0xF5.toUByte(), 0x77.toUByte(), 0x46.toUByte()),
                Color(0xF3.toUByte(), 0x77.toUByte(), 0x4F.toUByte()),
                Color(0xED.toUByte(), 0x5F.toUByte(), 0x55.toUByte())
            ),
            listOf(
                Color(0x0E.toUByte(), 0xF6.toUByte(), 0x05.toUByte()),
                Color(0x00.toUByte(), 0x00.toUByte(), 0x00.toUByte()),
                Color(0x0E.toUByte(), 0xF6.toUByte(), 0x05.toUByte()),
                Color(0xFC.toUByte(), 0x7F.toUByte(), 0x49.toUByte()),
                Color(0xF4.toUByte(), 0x69.toUByte(), 0x4E.toUByte()),
                Color(0x0E.toUByte(), 0xF6.toUByte(), 0x05.toUByte())
            ),
            listOf(
                Color(0xE6.toUByte(), 0x5E.toUByte(), 0x2B.toUByte()),
                Color(0xF0.toUByte(), 0x65.toUByte(), 0x15.toUByte()),
                Color(0xFC.toUByte(), 0x81.toUByte(), 0x28.toUByte()),
                Color(0xFF.toUByte(), 0x81.toUByte(), 0x51.toUByte()),
                Color(0xEF.toUByte(), 0x58.toUByte(), 0x4C.toUByte()),
                Color(0xE8.toUByte(), 0x7F.toUByte(), 0x8B.toUByte())
            )
        )

        private val BMP_TO_WRITE24 = getBmpToWrite(bitsPerPixel = 24)

        private val BMP_TO_WRITE32 = getBmpToWrite(bitsPerPixel = 32)

        private fun getBmpToWrite(bitsPerPixel: Int): ValidatedBmp {
            val width = 6
            val height = 6
            val bytesPerPixel = bitsPerPixel shr 3
            val sizeOfBitmap = ((bytesPerPixel * width + (4 - bytesPerPixel * width % 4) % 4) * height).toUInt()
            return Bmp(
                fileHeader = BmpFileHeader(
                    fileType = 19778.toUShort(),
                    fileSize = 54u + sizeOfBitmap,
                    reserved1 = 0.toUShort(),
                    reserved2 = 0.toUShort(),
                    bitmapOffset = 54u
                ),
                infoHeader = BmpInfoHeader(
                    size = 40u,
                    width = width,
                    height = height,
                    planes = 1.toUShort(),
                    bitsPerPixel = bitsPerPixel.toUShort(),
                    compression = 0u,
                    sizeOfBitmap = sizeOfBitmap,
                    horzResolution = 2834,
                    vertResolution = 2834,
                    colorsUsed = 0u,
                    colorsImportant = 0u
                ),
                pixels = PIXELS
            ).validated()
        }
    }
}
