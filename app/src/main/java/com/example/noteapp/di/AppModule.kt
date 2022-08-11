package com.example.noteapp.di

import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.source.NoteDao
import com.example.noteapp.data.source.NoteDatabase
import com.example.noteapp.data.source.repository.NoteRepository
import com.example.noteapp.data.source.repository.NoteRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,"NoteDB").build()


    @Singleton
    @Provides
    fun injectDao(database: NoteDatabase) = database.noteDao()

    @Singleton
    @Provides
    fun injectRepository(dao: NoteDao)=NoteRepository(dao) as NoteRepositoryInterface




}