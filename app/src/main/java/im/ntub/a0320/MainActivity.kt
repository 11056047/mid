//11056047 11056029 11056043
package im.ntub.a0320

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import im.ntub.a0320.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // 定義啟動 SecondActivity 的 ActivityResultLauncher
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // 從返回的 Intent 中獲取所選的大槍、小槍和護甲資料
            val selectedBigGun = result.data?.getStringExtra("selectedBigGun")
            val selectedSmallGun = result.data?.getStringExtra("selectedSmallGun")
            val lightShieldChecked = result.data?.getBooleanExtra("lightShieldChecked", false)
            val heavyShieldChecked = result.data?.getBooleanExtra("heavyShieldChecked", false)

            // 設置大槍、小槍和護甲的名稱和價格
            binding.bigamount.text = selectedBigGun ?: ""
            binding.smallamount.text = selectedSmallGun ?: ""
            binding.armoramount.text = if (lightShieldChecked == true) "輕型護盾 - $400" else if (heavyShieldChecked == true) "重型護盾 - $1000" else ""

            // 計算總價格
            var totalAmount = 0
            if (selectedBigGun != null) {
                totalAmount += parsePrice(selectedBigGun)
            }
            if (selectedSmallGun != null) {
                totalAmount += parsePrice(selectedSmallGun)
            }
            if (lightShieldChecked == true) {
                totalAmount += 400
            }
            if (heavyShieldChecked == true) {
                totalAmount += 1000
            }

            // 將總價格顯示在 totalamount 欄位中
            binding.textView11totalamount.text = "$totalAmount"
        }
    }

    // 解析價格的輔助函數
    private fun parsePrice(gunName: String): Int {
        val priceStr = gunName.substringAfter("$")
        return priceStr.toIntOrNull() ?: 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 設置按鈕的點擊事件
        binding.button1.setOnClickListener {
            // 創建一個 Intent 對象，用於啟動 SecondActivity
            val intent = Intent(this, secondActivity::class.java)
            // 啟動 SecondActivity
            launcher.launch(intent)
        }
    }
}
