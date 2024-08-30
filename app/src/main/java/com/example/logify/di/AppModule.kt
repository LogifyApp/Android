package com.example.logify.di

import android.content.Context
import androidx.room.Room
import com.example.logify.dao.AppDatabase
import com.example.logify.dao.CargoDao
import com.example.logify.dao.ChatLastOpenedDao
import com.example.logify.dao.DocumentDao
import com.example.logify.dao.TokenDao
import com.example.logify.dao.UserDao
import com.example.logify.repository.CargoRepository
import com.example.logify.repository.DocumentRepository
import com.example.logify.repository.MessageRepository
import com.example.logify.repository.TokenRepository
import com.example.logify.repository.UserRepository
import com.example.logify.services.ApiMessageService
import com.example.logify.services.CargoService
import com.example.logify.services.MessageService
import com.example.logify.services.TokenService
import com.example.logify.services.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://yourapi.com"

    @Provides
    @Singleton
    fun provideUserService(): UserService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideTokenService(): TokenService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(TokenService::class.java)
    }

    @Provides
    @Singleton
    fun provideCargoService(): CargoService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CargoService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiMessageService(): ApiMessageService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiMessageService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "logify_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideTokenDao(database: AppDatabase): TokenDao {
        return database.tokenDao()
    }

    @Provides
    @Singleton
    fun provideCargoDao(database: AppDatabase): CargoDao {
        return database.cargoDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userService: UserService, userDao: UserDao): UserRepository {
        return UserRepository(userService, userDao)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(tokenService: TokenService, tokenDao: TokenDao): TokenRepository {
        return TokenRepository(tokenService, tokenDao)
    }

    @Provides
    @Singleton
    fun provideCargoRepository(cargoService: CargoService, cargoDao: CargoDao): CargoRepository {
        return CargoRepository(cargoService, cargoDao)
    }

    @Provides
    @Singleton
    fun provideMessageRepository(
        apiMessageService: ApiMessageService,
        chatLastOpenedDao: ChatLastOpenedDao
    ): MessageRepository {
        return MessageRepository(apiMessageService, chatLastOpenedDao)
    }

    @Provides
    @Singleton
    fun provideMessageService(repository: MessageRepository): MessageService {
        return MessageService(repository)
    }
    @Provides
    @Singleton
    fun provideChatLastOpenedDao(database: AppDatabase): ChatLastOpenedDao {
        return database.chatLastOpenedDao()
    }

    @Provides
    @Singleton
    fun provideDocumentDao(database: AppDatabase): DocumentDao {
        return database.documentDao()
    }

    @Provides
    @Singleton
    fun provideDocumentRepository(
        @ApplicationContext context: Context,
        documentDao: DocumentDao
    ): DocumentRepository {
        return DocumentRepository(context, documentDao)
    }
}