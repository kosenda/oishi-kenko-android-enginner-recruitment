package com.oishikenko.android.recruitment.feature.list.convert

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ConvertRecordedAtKtTest {
    @Test
    fun give_recordedAt_remove_last_3_letter() {
        assert("2022/11/07 00:45" == convertRecordedAt(recorded_at = "2022/11/07 00:45:01"))
    }
}