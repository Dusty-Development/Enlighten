package net.dustley.enlighten.component

import org.ladysnake.cca.api.v3.component.Component
import java.util.*

interface UUIDComponent : Component {
    fun getValue():UUID
    fun setValue(value:UUID)

    fun getIsAssigned(): Boolean
    fun setIsAssigned(value: Boolean)

    fun getDeathCount(): Int
    fun setDeathCount(value: Int)
}