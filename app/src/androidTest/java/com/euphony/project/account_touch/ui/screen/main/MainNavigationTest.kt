//package com.euphony.project.account_touch.ui.screen.main
//
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithContentDescription
//import androidx.navigation.compose.ComposeNavigator
//import androidx.navigation.testing.TestNavHostController
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//class MainNavigationTest {
//    @get:Rule
//    val composeTestRule = createComposeRule()
//    lateinit var navController: TestNavHostController
//
//    @Before
//    fun MainNavHost_호출() {
//        composeTestRule.setContent {
//            navController = TestNavHostController(LocalContext.current)
//            navController.navigatorProvider.addNavigator(ComposeNavigator())
//            MainNavHost(navController = navController)
//        }
//    }
//
//    @Test
//    fun 시작화면() {
//        composeTestRule
//            .onNodeWithContentDescription("MainBottomSheet screen")
//            .assertIsDisplayed()
//    }
//
//    @Test
//    fun 송신계좌화면으로_이동() { // TODO: 메인화면 머지 후 작성
//
//    }
//
//    @Test
//    fun 수신계좌리스트화면으로_이동() { // TODO: 메인화면 머지 후 작성
//
//    }
//}
