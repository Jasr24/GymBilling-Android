package com.jasrdeveloper.gymbilling.utils.globals

object GlobalsVar {

   var userIsLogged: Boolean = false

    /* Development variables */
    const val GlobalsVar_debugAppHTTP: Boolean = true
    const val GlobalsVar_baseUrlCustomerREST: String = ""

    /* Production variables */
    /*const val GlobalsVar_debugAppHTTP: Boolean = false
    const val GlobalsVar_baseUrlCustomerREST: String = ""*/


    /* Globals variables */
    const val GlobalsVar_logTag: String = "TAG"
    const val GlobalsVar_contentTypeREST: String = "application/json"
    const val GlobalsVar_showLogsInterceptor: Boolean = true
    const val GlobalsVar_debugAppSSL: Boolean = true

    const val GlobalsVar_baseUrlGenericStore: String = "${GlobalsVar_baseUrlCustomerREST}generic/store"
    const val GlobalsVar_baseUrlGenericTable: String = "${GlobalsVar_baseUrlCustomerREST}generic/gettable"

}