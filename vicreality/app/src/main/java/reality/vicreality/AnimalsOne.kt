package reality.vicreality

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.ar.core.Anchor
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_animals_one.*

class AnimalsOne : AppCompatActivity() {

    private lateinit var arFragment: ArFragment
    private var selectedObject: Int = 0

    private lateinit var bear: ImageView
    private lateinit var cat: ImageView
    private lateinit var cow: ImageView
    private lateinit var dog: ImageView
    private lateinit var elephant: ImageView
    private lateinit var ferret: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animals_one)

        animals_one_return.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        arFragment = supportFragmentManager.findFragmentById(sceneform_ux_fragment.id) as ArFragment

        bear = findViewById(R.id.bear)
        cat = findViewById(R.id.cat)
        cow = findViewById(R.id.cow)
        dog = findViewById(R.id.dog)
        elephant = findViewById(R.id.elephant)
        ferret = findViewById(R.id.ferret)

        bear.setOnClickListener {
            setModelName("Buttons the Bear")
            selectedObject = R.raw.bear
        }

        cat.setOnClickListener {
            setModelName("Callie the Cat")
            selectedObject = R.raw.cat
        }

        cow.setOnClickListener {
            setModelName("Charlie the Cow")
            selectedObject = R.raw.cow
        }

        dog.setOnClickListener {
            setModelName("Daisy the Dog")
            selectedObject = R.raw.dog
        }

        elephant.setOnClickListener {
            setModelName("Ellie the Elephant")
            selectedObject = R.raw.elephant
        }

        ferret.setOnClickListener {
            setModelName("Foxy the Ferret")
            selectedObject = R.raw.ferret
        }

        arFragment.setOnTapArPlaneListener { hitResult, plane, _ ->
            if (plane.type != Plane.Type.HORIZONTAL_UPWARD_FACING) {
                return@setOnTapArPlaneListener
            }
            val anchor = hitResult.createAnchor()
            placeObject(arFragment, anchor, selectedObject)
        }
    }

    private fun placeObject(fragment: ArFragment, anchor: Anchor, model: Int) {
        val modelRenderable = ModelRenderable.builder()
            .setSource((fragment.requireContext()), model)
            .build()
        modelRenderable.thenAccept { renderableObject ->
            addNodeToScene(
                fragment,
                anchor,
                renderableObject
            )
        }
        modelRenderable.exceptionally {
            val toast = Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
            toast.show()
            null
        }
    }

    private fun addNodeToScene(fragment: ArFragment, anchor: Anchor, renderableObject: Renderable) {
        val anchorNode = AnchorNode(anchor)
        val transformableNode = TransformableNode(fragment.transformationSystem)
        transformableNode.renderable = renderableObject
        transformableNode.setParent(anchorNode)
        fragment.arSceneView.scene.addChild(anchorNode)
        transformableNode.select()
    }

    private fun setModelName(modelFileName: String) {
        val toast = Toast.makeText(applicationContext, modelFileName, Toast.LENGTH_SHORT)
        toast.show()
    }
}