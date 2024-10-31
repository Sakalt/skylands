package org.lolhens.skylands.entity

import net.minecraft.entity.monster.EntitySlime
import net.minecraft.world.World
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraftforge.fml.common.registry.EntityRegistry
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData
import net.minecraft.entity.EntityLivingBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer

class EntitySkySlime(world: World) extends EntitySlime(world) with IEntityAdditionalSpawnData {

  // コンストラクタ
  def this() = this(null)

  this.setSize(1.0F, 1.0F)  // サイズを設定
  this.setHealth(15.0F)     // HPを設定
  this.experienceValue = 5  // 倒されたときの経験値

  // 浮遊のような挙動を追加
  override def onUpdate(): Unit = {
    super.onUpdate()
    this.motionY = 0.02  // ゆっくり浮き上がるようにする
  }

  override def getSlimeSize(): Int = {
    return 2 // スライムのサイズをカスタム
  }

  // スポーンデータの保存
  override def writeSpawnData(data: PacketBuffer): Unit = {
    data.writeFloat(this.getHealth) // HPを保存
    // 他のカスタムデータをここに追加することができます
  }

  // スポーンデータの読み込み
  override def readSpawnData(data: PacketBuffer): Unit = {
    this.setHealth(data.readFloat()) // HPを復元
    // 他のカスタムデータをここに追加することができます
  }

  // スカイスライムのサウンドイベント
  override def getAmbientSound: SoundEvent = {
    new SoundEvent(new ResourceLocation("skylands:sky_slime_ambient"))
  }

  override def getHurtSound: SoundEvent = {
    new SoundEvent(new ResourceLocation("skylands:sky_slime_hurt"))
  }

  override def getDeathSound: SoundEvent = {
    new SoundEvent(new ResourceLocation("skylands:sky_slime_death"))
  }
}
