package im.ntub.a0320

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import im.ntub.a0320.databinding.ActivitySecondBinding
import android.app.AlertDialog

class secondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClose.setOnClickListener {
            showConfirmationDialog()
        }

        // 设置取消按钮的点击事件
        binding.button.setOnClickListener {
            resetSelection()
        }
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("確認執行")
        builder.setMessage("是否確定執行？")
        builder.setPositiveButton("確定") { _, _ ->
            handleConfirmation()
        }
        builder.setNegativeButton("取消", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun handleConfirmation() {
        // 在這裡處理按下確認按鈕後的邏輯
        // 檢查所選的大槍
        val bigRadioButtonId = binding.radioGroup.checkedRadioButtonId
        val bigRadioButton = findViewById<RadioButton>(bigRadioButtonId)
        val selectedBigGun = bigRadioButton?.text.toString()

        // 檢查所選的小槍
        val smallRadioButtonId = binding.radioGroup2.checkedRadioButtonId
        val smallRadioButton = findViewById<RadioButton>(smallRadioButtonId)
        val selectedSmallGun = smallRadioButton?.text.toString()

        // 檢查是否選擇了護甲
        val lightShieldChecked = binding.light.isChecked
        val heavyShieldChecked = binding.heavy.isChecked

        // 創建一個 Intent 將所選的大槍、小槍和護甲放入其中
        val intent = Intent()
        intent.putExtra("selectedBigGun", selectedBigGun)
        intent.putExtra("selectedSmallGun", selectedSmallGun)
        intent.putExtra("lightShieldChecked", lightShieldChecked)
        intent.putExtra("heavyShieldChecked", heavyShieldChecked)


        // 設置結果為 RESULT_OK 並返回 Intent
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun resetSelection() {
        // 重置所选项
        binding.radioGroup.clearCheck()
        binding.radioGroup2.clearCheck()
        binding.light.isChecked = false
        binding.heavy.isChecked = false
    }
}
