package com.example.movieapp.Modules

import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.movieapp.data.remote.MovieApi
import com.example.movieapp.data.repository.Repository
import com.example.movieapp.screens.account.AccountViewModel
import com.example.movieapp.screens.details.DetailsViewModel
import com.example.movieapp.screens.home.HomeViewModel
import com.example.movieapp.screens.search.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val USER_PREFERENCES = "user_preferences"

val movieModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
    single {
        Repository(get())
    }
    single {
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { androidContext().preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }
    viewModel {
        AccountViewModel(get(), get())
    }
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        SearchViewModel(get())
    }
    viewModel {
        DetailsViewModel(get(), get())
    }
}