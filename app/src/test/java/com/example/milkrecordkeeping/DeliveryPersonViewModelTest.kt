package com.example.milkrecordkeeping

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.milkrecordkeeping.database.MilkDeliveryDatabase
import com.example.milkrecordkeeping.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeliveryPersonViewModelTest {

    private lateinit var deliveryPersonViewModel: DeliveryPersonViewModel
    private lateinit var database: MilkDeliveryDatabase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MilkDeliveryDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        deliveryPersonViewModel = DeliveryPersonViewModel(database.agentDao())
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `should show all agents from the table`() {
        deliveryPersonViewModel.add(
            name = "test",
            phone = "9878882888",
            rate = "32",
            address = "testAddress"
        )

        assertThat(deliveryPersonViewModel.allAgents.getOrAwaitValue()).hasSize(1)
    }

    @Test
    fun testGetAgent() {
        deliveryPersonViewModel.add(
            name = "test",
            phone = "9878882888",
            rate = "32",
            address = "testAddress"
        )

        assertThat(deliveryPersonViewModel.getAgent(1).getOrAwaitValue()).isNotNull()
    }

    @Test
    fun testIsEntryValid() {
        assertThat(deliveryPersonViewModel.isEntryValid("", "", "")).isFalse()
        assertThat(deliveryPersonViewModel.isEntryValid("", "", "someRate")).isFalse()
        assertThat(deliveryPersonViewModel.isEntryValid("", "somemobile", "someRate")).isFalse()
        assertThat(
            deliveryPersonViewModel.isEntryValid(
                "someName",
                "somemobile",
                "someRate"
            )
        ).isTrue()
    }
}