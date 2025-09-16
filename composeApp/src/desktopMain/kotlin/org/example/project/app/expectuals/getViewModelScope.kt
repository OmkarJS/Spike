package org.example.project.app.expectuals

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.swing.Swing

actual fun getViewModelScope(): CoroutineScope =
    CoroutineScope(SupervisorJob() + Dispatchers.Swing)