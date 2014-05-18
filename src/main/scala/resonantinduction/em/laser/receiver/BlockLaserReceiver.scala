package resonantinduction.em.laser.receiver

import net.minecraft.block.{BlockPistonBase, BlockContainer}
import net.minecraft.block.material.Material
import resonantinduction.em.ElectromagneticCoherence
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.world.{IBlockAccess, World}
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import resonantinduction.em.laser.emitter.TileLaserReceiver
import resonantinduction.em.laser.BlockRenderingHandler

/**
 * @author Calclavia
 */
class BlockLaserReceiver extends BlockContainer(Material.rock)
{
  setBlockName(ElectromagneticCoherence.PREFIX + "laserReceiver")
  setBlockTextureName("stone")
  setCreativeTab(CreativeTabs.tabRedstone)

  /**
   * Called when the block is placed in the world.
   */
  override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entity: EntityLivingBase, itemStack: ItemStack)
  {
    val l = BlockPistonBase.determineOrientation(world, x, y, z, entity)
    world.setBlockMetadataWithNotify(x, y, z, l, 2)
  }

  override def getMixedBrightnessForBlock(access: IBlockAccess, x: Int, y: Int, z: Int): Int =
  {
    return access.getTileEntity(x, y, z).asInstanceOf[TileLaserReceiver].redstoneValue
  }

  override def isProvidingWeakPower(access: IBlockAccess, x: Int, y: Int, z: Int, metadata: Int): Int =
  {
    return isProvidingStrongPower(access, x, y, z, metadata)
  }

  override def isProvidingStrongPower(access: IBlockAccess, x: Int, y: Int, z: Int, metadata: Int): Int =
  {
    return access.getTileEntity(x, y, z).asInstanceOf[TileLaserReceiver].redstoneValue
  }

  override def createNewTileEntity(world: World, metadata: Int): TileEntity =
  {
    return new TileLaserReceiver()
  }

  override def getRenderType = BlockRenderingHandler.getRenderId

  override def canProvidePower: Boolean = false

  override def renderAsNormalBlock = false

  override def isOpaqueCube = false
}

