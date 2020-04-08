package reality.vicreality

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        pokemon_button.setOnClickListener {
            val intent = Intent(this, AnimalsOne::class.java)
            startActivity(intent)
        }

        items_button.setOnClickListener {
            val intent = Intent(this, AnimalsTwo::class.java)
            startActivity(intent)
        }

    }
}
