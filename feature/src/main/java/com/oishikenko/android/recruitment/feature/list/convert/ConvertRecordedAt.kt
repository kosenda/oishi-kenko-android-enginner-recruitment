package com.oishikenko.android.recruitment.feature.list.convert


/**
 * レスポンスから取得した記録日時の秒数を削除する（後ろから３文字消すだけの）処理
 *  @param recorded_at レスポンスから取得した記録日時
 *  @return 変換後の記録日時
 */
fun convertRecordedAt(recorded_at: String): String {
    return recorded_at.substring(0, recorded_at.length - 3)
}