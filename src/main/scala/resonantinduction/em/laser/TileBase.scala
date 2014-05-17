package resonantinduction.em.laser

import net.minecraftforge.common.util.ForgeDirection
import resonantinduction.em.Vector3
import net.minecraft.tileentity.TileEntity

/**
 * @author Calclavia
 */
class TileBase extends TileEntity
{
  def world = worldObj

  def isPowered(): Boolean = getWorldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)

  def direction: ForgeDirection = ForgeDirection.getOrientation(getBlockMetadata)

  def position: Vector3 = new Vector3(this)

  def x = xCoord

  def y = yCoord

  def z = zCoord
}
