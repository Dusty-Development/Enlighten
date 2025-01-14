package net.dustley.enlighten.network

import net.dustley.enlighten.network.s2c.SnareStoneClientPacketPayload
import net.dustley.enlighten.network.s2c.SnareStoneClientPacketPayload.Companion.SNARE_STONE_CLIENT_PACKET_ID
import net.dustley.enlighten.network.s2c.SnatchSlateClientPacketPayload
import net.dustley.enlighten.network.s2c.SnatchSlateClientPacketPayload.Companion.SNATCH_SLATE_CLIENT_PACKET_ID
import net.dustley.enlighten.network.s2c.WishKillClientPacketPayload
import net.dustley.enlighten.network.s2c.WishKillClientPacketPayload.Companion.WISH_KILL_CLIENT_PACKET_ID
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry


object ModNetworking {

    fun registerCommon() {
//        PayloadTypeRegistry.playS2C().register(WISH_CONTRACT_CLIENT_PACKET_ID, WishContractClientPacketPayload.CODEC)
//        PayloadTypeRegistry.playS2C().register(ENLIGHTENMENT_CONTRACT_CLIENT_PACKET_ID, EnlightenmentContractClientPacketPayload.CODEC)
        PayloadTypeRegistry.playS2C().register(WISH_KILL_CLIENT_PACKET_ID, WishKillClientPacketPayload.CODEC)
        PayloadTypeRegistry.playS2C().register(SNATCH_SLATE_CLIENT_PACKET_ID, SnatchSlateClientPacketPayload.CODEC)
        PayloadTypeRegistry.playS2C().register(SNARE_STONE_CLIENT_PACKET_ID, SnareStoneClientPacketPayload.CODEC)
    }

    fun registerClient() {
//        ClientPlayNetworking.registerGlobalReceiver(WISH_CONTRACT_CLIENT_PACKET_ID, WishContractClientPacketPayload::receive)
//        ClientPlayNetworking.registerGlobalReceiver(ENLIGHTENMENT_CONTRACT_CLIENT_PACKET_ID, EnlightenmentContractClientPacketPayload::receive)
        ClientPlayNetworking.registerGlobalReceiver(WISH_KILL_CLIENT_PACKET_ID, WishKillClientPacketPayload::receive)
        ClientPlayNetworking.registerGlobalReceiver(SNATCH_SLATE_CLIENT_PACKET_ID, SnatchSlateClientPacketPayload::receive)
        ClientPlayNetworking.registerGlobalReceiver(SNARE_STONE_CLIENT_PACKET_ID, SnareStoneClientPacketPayload::receive)
    }
}