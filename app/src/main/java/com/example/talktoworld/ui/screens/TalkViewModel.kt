package com.example.talktoworld.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.talktoworld.data.MarsRepository
import com.example.talktoworld.TalkToWorldApplication
import com.example.talktoworld.model.MarsPhoto

import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface TalkUiState{
    data class Success(val photos:List<MarsPhoto>): TalkUiState
    object Error: TalkUiState
    object Loading : TalkUiState

}
class TalkViewModel(private val marsPhotoRepository: MarsRepository): ViewModel() {
    var talkUiState: TalkUiState by mutableStateOf(TalkUiState.Loading)
               private set
    init{
        //("GFHHHHHHHHHHHHHHHHHHHHHHHHHH")
        getMarsPhoto()
        //println("GFHHHHHHHHHHHHHHHHHHHHHHHHHH")
        //println(marsUiState+"HHHHHHHHHHHHHHHHHHH")
    }
    fun getMarsPhoto(){
        viewModelScope.launch{
            talkUiState =  try {

                val listResult = marsPhotoRepository.getMarsPhoto()
                //println("$listResult")
                TalkUiState.Success(listResult)
            }catch (_:IOException){
                TalkUiState.Error
            }catch (e: HttpException) {
                TalkUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TalkToWorldApplication)
                val marsPhotosRepository = application.container.marsPhotoRepository
                TalkViewModel(marsPhotosRepository)
            }
        }
    }



}