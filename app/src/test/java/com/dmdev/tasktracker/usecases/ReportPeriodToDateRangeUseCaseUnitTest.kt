package com.dmdev.tasktracker.usecases

import com.dmdev.tasktracker.data.domain.DateRange
import com.dmdev.tasktracker.data.domain.ReportPeriod
import com.dmdev.tasktracker.ui.utils.time_provider.TimeProvider
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.util.*

class ReportPeriodToDateRangeUseCaseUnitTest {

    private val mockTimeProvider: TimeProvider = mock()

    @Test
    fun `UseCase return right range of last 7 days period for 02-01-122 date`() {
        //Arrange
        Mockito.`when`(mockTimeProvider.getCurrentDate()).thenReturn(
            Date(122, 0, 2)
        )

        val useCase = ReportPeriodToDateRangeUseCase(mockTimeProvider)

        //Act
        val result = useCase.execute(ReportPeriod.LAST_SEVEN_DAYS)

        val start = Date(121, 11, 26)
        val end = Calendar.getInstance().apply {
            time = Date(122, 0, 2)
            add(Calendar.MILLISECOND, -1)
        }.time
        val range = DateRange(start, end)
        //Assert
        assert(result == range)
    }

    @Test
    fun `UseCase return right range of last 7 days period for 08-03-122 date`() {
        //Arrange
        Mockito.`when`(mockTimeProvider.getCurrentDate()).thenReturn(
            Date(122, 2, 8)
        )

        val useCase = ReportPeriodToDateRangeUseCase(mockTimeProvider)

        //Act
        val result = useCase.execute(ReportPeriod.LAST_SEVEN_DAYS)

        val start = Date(122, 2, 1)
        val end = Calendar.getInstance().apply {
            time = Date(122, 2, 8)
            add(Calendar.MILLISECOND, -1)
        }.time
        val range = DateRange(start, end)
        //Assert
        assert(result == range)
    }

    @Test
    fun `UseCase return right range of 30 days period for 08-03-122 date `() {
        //Arrange
        Mockito.`when`(mockTimeProvider.getCurrentDate()).thenReturn(
            Date(122, 2, 8)
        )

        val useCase = ReportPeriodToDateRangeUseCase(mockTimeProvider)

        //Act
        val result = useCase.execute(ReportPeriod.LAST_THIRTY_DAYS)

        val start = Date(122, 1, 6)
        val end = Calendar.getInstance().apply {
            time = Date(122, 2, 8)
            add(Calendar.MILLISECOND, -1)
        }.time
        val range = DateRange(start, end)
        //Assert
        assert(result == range)
    }

    @Test
    fun `UseCase return right range of last month period for 08-03-122 date `() {
        //Arrange
        Mockito.`when`(mockTimeProvider.getCurrentDate()).thenReturn(
            Date(122, 2, 8)
        )

        val useCase = ReportPeriodToDateRangeUseCase(mockTimeProvider)

        //Act
        val result = useCase.execute(ReportPeriod.MONTH)

        val start = Date(122, 1, 1)
        val end = Calendar.getInstance().apply {
            time = Date(122, 2, 1)
            add(Calendar.MILLISECOND, -1)
        }.time

        val range = DateRange(start, end)
        //Assert
        assert(result == range)
    }


    @Test
    fun `UseCase return right range of Quarter period for 08-03-122 date `() {
        //Arrange
        Mockito.`when`(mockTimeProvider.getCurrentDate()).thenReturn(
            Date(122, 2, 8)
        )

        val useCase = ReportPeriodToDateRangeUseCase(mockTimeProvider)

        //Act
        val result = useCase.execute(ReportPeriod.QUARTER)

        val start = Date(121, 9, 1)
        val end = Calendar.getInstance().apply {
            time = Date(122, 0, 1)
            add(Calendar.MILLISECOND, -1)
        }.time
        val range = DateRange(start, end)
        //Assert
        assert(result == range)
    }

    @Test
    fun `UseCase return right range of Year period for 08-03-122 date `() {
        //Arrange
        Mockito.`when`(mockTimeProvider.getCurrentDate()).thenReturn(
            Date(122, 2, 8)
        )

        val useCase = ReportPeriodToDateRangeUseCase(mockTimeProvider)

        //Act
        val result = useCase.execute(ReportPeriod.YEAR)

        val start = Date(121, 0, 1)
        val end = Calendar.getInstance().apply {
            time = Date(122, 0, 1)
            add(Calendar.MILLISECOND, -1)
        }.time

        val range = DateRange(start, end)
        //Assert
        assert(result == range)
    }
}