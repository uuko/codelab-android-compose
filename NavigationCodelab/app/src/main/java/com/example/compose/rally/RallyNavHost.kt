package com.example.compose.rally

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose.rally.ui.accounts.AccountsScreen
import com.example.compose.rally.ui.accounts.SingleAccountScreen
import com.example.compose.rally.ui.bills.BillsScreen
import com.example.compose.rally.ui.overview.OverviewScreen


@Composable
fun RallyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {
        composable(route = Overview.route) {
//                    Overview.screen()
//                    OverviewScreen()
            OverviewScreen(
                onClickSeeAllAccounts = {
                    navController.navigateSingleTopTo(Accounts.route)
                },
                onClickSeeAllBills = {
                    navController.navigateSingleTopTo(Bills.route)
                },
                onAccountClick = { accountType ->
                    navController
                        .navigateSingleTopTo("${SingleAccount.route}/$accountType")
                }
            )
        }
        composable(route = Accounts.route) {
//                    Accounts.screen()
            AccountsScreen(
                onAccountClick = { accountType ->
                    navController
                        .navigateSingleTopTo("${SingleAccount.route}/$accountType")
                }
            )

        }
        composable(route = Bills.route) {
//                    Bills.screen()
            BillsScreen()
        }

        composable(
            route = SingleAccount.routeWithArgs,
            arguments = SingleAccount.arguments,
            deepLinks = SingleAccount.deepLinks
        ) { navBackStackEntry ->
            val accountType =
                navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
            SingleAccountScreen(accountType)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

private fun NavHostController.navigateToSingleAccount(accountType: String) {
    this.navigateSingleTopTo("${SingleAccount.route}/$accountType")
}

//fun NavHostController.navigateSingleTopTo(route: String) =
//    this.navigate(route) {
////        popUpTo(
////            this@navigateSingleTopTo.graph.findStartDestination().id
////        ) {
////            saveState = true
////        }
//        launchSingleTop = true
//        restoreState = true
//    }