package pl.szjug.akka.c1.onethreaded

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import com.mkrcah.fractals._
import com.typesafe.scalalogging.LazyLogging
import pl.szjug.akka.fractals.JuliaRenderer

object RunSingleThread extends App with LazyLogging {

  private val imageSize = Size2i(1000, 700)
  private val quality = 300

  logger.info("Starting!")

  val renderer = new JuliaRenderer(imageSize, HuePalette, quality, new Region2i(imageSize))
  val colorsForPixels = renderer.render()

  val img = new BufferedImage(imageSize.width, imageSize.height, BufferedImage.TYPE_INT_RGB)
  for((pixel, color) <- colorsForPixels.pixels) {
    img.setRGB(pixel.x, pixel.y, color.toRGB.toInt)
  }

  logger.info("Writing to file")
  ImageIO.write(img, "png", new File(s"julia$quality$imageSize.png"))
}