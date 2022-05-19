package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.ResultWrapper
import com.dmdev.tasktracker.data.entities.CategoryEntity
import com.dmdev.tasktracker.data.CategoryIcon
import com.dmdev.tasktracker.data.Colors
import com.dmdev.tasktracker.data.domain.Category
import com.dmdev.tasktracker.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FakeCategoriesRepository : CategoriesRepository {
    override suspend fun getAllCategories(): Flow<ResultWrapper<List<CategoryEntity>>> = flow {
        emit(ResultWrapper.Success(listOf(
            CategoryEntity(1, "Test", Colors.CIAN.value.value.toLong(), CategoryIcon.BROOM),
            CategoryEntity(2, "Test2", Colors.AMBER.value.value.toLong(), CategoryIcon.BROOM)
        )))
    }

    override suspend fun add(category: CategoryEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun update(category: CategoryEntity) {
        TODO("Not yet implemented")
    }
}

class GetAllCategoriesUseCaseUnitTest {

    private val useCase = GetAllCategoriesUseCase(FakeCategoriesRepository())

    @Test
    fun `UseCase return the same items mapped to the domain model`() = runBlocking {
        //Arrange

        //Act
        val result = useCase.execute().toList()

        //Assert
        assertTrue(result.last() is ResultWrapper.Success<*>)

        val expectedList = listOf(
            Category(1, "Test", Colors.CIAN, CategoryIcon.BROOM),
            Category(2, "Test2", Colors.AMBER, CategoryIcon.BROOM)
        )
        assertEquals(expectedList, (result.last() as ResultWrapper.Success).result)
    }
}