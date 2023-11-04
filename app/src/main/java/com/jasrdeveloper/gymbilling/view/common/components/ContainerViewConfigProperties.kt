package com.jasrdeveloper.gymbilling.view.common.components

import com.jasrdeveloper.gymbilling.utils.enums.LayoutViewTypes

data class ContainerViewConfigProperties(
    val showHeader: Boolean = true,
    val showTitle: Boolean = true,
    val title: String,
    val headerType: LayoutViewTypes = LayoutViewTypes.DEFAULT_LAYOUT
)