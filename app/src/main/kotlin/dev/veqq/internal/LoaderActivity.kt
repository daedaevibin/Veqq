package dev.veqq.internal

import android.app.Activity
import android.os.Bundle

/**
 * Internal loader entry. No cross-client loader identity.
 * Replaces ShiggyCord entry.ts deferred-call mechanism.
 */
class LoaderActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Internal loader initialization (Vector/LSPatch embedded runtime)
        finish()
    }
}
