package resonantinduction.em.laser.emitter

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.client.model.AdvancedModelLoader
import resonantinduction.em.ElectromagneticCoherence
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11._
import cpw.mods.fml.client.FMLClientHandler
import net.minecraftforge.common.util.ForgeDirection
import cpw.mods.fml.relauncher.{Side, SideOnly}

/**
 * @author Calclavia
 */
@SideOnly(Side.CLIENT)
object RenderLaserEmitter extends TileEntitySpecialRenderer
{
  val model = AdvancedModelLoader.loadModel(new ResourceLocation(ElectromagneticCoherence.DOMAIN, ElectromagneticCoherence.MODEL_PATH_NAME + "laserEmitter.tcn"))
  val texture = new ResourceLocation(ElectromagneticCoherence.DOMAIN, ElectromagneticCoherence.MODEL_PATH_NAME + "laserEmitter.png")

  def renderTileEntityAt(tileEntity: TileEntity, x: Double, y: Double, z: Double, f: Float)
  {
    glPushMatrix()
    glTranslated(x + 0.5, y + 0.5, z + 0.5)

    glShadeModel(GL_SMOOTH)
    glEnable(GL_BLEND)
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)

    tileEntity.asInstanceOf[TileLaserEmitter].direction match
    {
      case ForgeDirection.UNKNOWN =>
      case ForgeDirection.UP => glRotatef(-90, 1, 0, 0)
      case ForgeDirection.DOWN => glRotatef(90, 1, 0, 0)
      case ForgeDirection.NORTH => glRotatef(90, 0, 1, 0)
      case ForgeDirection.SOUTH => glRotatef(-90, 0, 1, 0)
      case ForgeDirection.WEST => glRotatef(-180, 0, 1, 0)
      case ForgeDirection.EAST => glRotatef(0, 0, 1, 0)
    }

    if (tileEntity.asInstanceOf[TileLaserEmitter].direction.offsetY == 0)
      glRotatef(-90, 0, 1, 0)
    else
      glRotatef(180, 1, 0, 0)

    FMLClientHandler.instance.getClient.renderEngine.bindTexture(texture)
    model.renderAll()

    glShadeModel(GL_FLAT)
    glDisable(GL_LINE_SMOOTH)
    glDisable(GL_POLYGON_SMOOTH)
    glDisable(GL_BLEND)

    GL11.glPopMatrix()
  }

  def renderItem()
  {
    glPushMatrix()
    glRotated(180, 0, 1, 0)

    glShadeModel(GL_SMOOTH)
    glEnable(GL_BLEND)
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)

    FMLClientHandler.instance.getClient.renderEngine.bindTexture(texture)
    model.renderAll()

    glShadeModel(GL_FLAT)
    glDisable(GL_LINE_SMOOTH)
    glDisable(GL_POLYGON_SMOOTH)
    glDisable(GL_BLEND)

    GL11.glPopMatrix()
  }
}
