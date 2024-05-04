package com.example.talktoworld.data

import com.example.talktoworld.model.MarsPhoto
import com.example.talktoworld.network.TalkApiService


interface MarsRepository {
    suspend fun getMarsPhoto(): List<MarsPhoto>
}

class NetworkMarsPhotoRepository(private val retrofitService: TalkApiService): MarsRepository{
    override suspend fun getMarsPhoto(): List<MarsPhoto> {
        return retrofitService.getPhotos()
    }

}