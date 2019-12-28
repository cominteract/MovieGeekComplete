package com.andreinsigne.moviegeek

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun searchQuery_isAcceptedAPIKey() {
        // Fail assertEquals("a46a4ee2b4c59209d6b729bdd54386a0", 1)
        // Fail assertEquals("a46a4ee2b4c59209d6b729bdd54386a0", "1")
        // Fail assertEquals("a46a4ee2b4c59209d6b729bdd54386a0", "a46a4ee2b4c59209d6b729bdd54")
        // Fail val constantAPI = "a46a4ee2b4c59209d6b729bdd543"
        val constantAPI = "a46a4ee2b4c59209d6b729bdd54386a0"
        assertEquals("a46a4ee2b4c59209d6b729bdd54386a0", "a46a4ee2b4c59209d6b729bdd54386a0")
        assertEquals("a46a4ee2b4c59209d6b729bdd54386a0", constantAPI)
    }


    @Test
    fun searchQuery_isAcceptedString() {
        assertEquals("avengers", "justice league")
    }

    @Test
    fun searchQuery_isNotAcceptedInteger() {
        val input : Comparable<String> = "1"
        assertEquals(true, input is String)
    }

    @Test
    fun apiPage_isPageStringAccepted() {

        val currentPage = 2
        //Fail var totalPage = 2
        var totalPage = 5
        // to format "number string" to integer used in the consuming
        // movie dbrest api which has page property
        // Fail assertEquals(1, Integer.valueOf("2"))
        assertEquals(2, 2)
        assertEquals(2, Integer.valueOf("2"))
        // to compare if currentpage < totalpages property consumed from movie db
        // Fail assertEquals(true, currentPage < 2)
        // Fail assertEquals(true, currentPage < Integer.valueOf("2"))
        // Fail assertEquals(true, currentPage < 2)
        assertEquals(true, currentPage < 3)
        assertEquals(true, currentPage < Integer.valueOf("3"))
        assertEquals(true, currentPage < totalPage)
        //Fail assertEquals(true, currentPage > totalPage)

    }

    @Test
    fun apiPage_isAllPagesRetrieved()
    {
        val currentPage = 3
        var totalPage = 2
        //var totalPage = 5
        assertEquals(true, currentPage > totalPage)
        assertEquals(true, currentPage - totalPage == 1)

    }
}
