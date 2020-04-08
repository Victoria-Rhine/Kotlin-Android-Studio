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
import kotlinx.android.synthetic.main.activity_animals_two.*

class AnimalsTwo : AppCompatActivity() {

    private lateinit var arFragment: ArFragment
    private var selectedObject: Int = 0

    private lateinit var hippo: ImageView
    private lateinit var horse: ImageView
    private lateinit var koala: ImageView
    private lateinit var lion: ImageView
    private lateinit var reindeer: ImageView
    private lateinit var wolverine: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animals_two)

        animals_two_return.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }

        arFragment = supportFragmentManager.findFragmentById(sceneform_ux_fragment.id) as ArFragment

        hippo = findViewById(R.id.hippo)
        horse = findViewById(R.id.horse)
        koala = findViewById(R.id.koala)
        lion = findViewById(R.id.lion)
        reindeer = findViewById(R.id.reindeer)
        wolverine = findViewById(R.id.wolverine)

        hippo.setOnClickListener {
            setModelName("Harry the Hippo")
            selectedObject = R.raw.hippopotamus
        }

        horse.setOnClickListener {
            setModelName("Helen the Horse")
            selectedObject = R.raw.horse
        }

        koala.setOnClickListener {
            setModelName("Kayla the Koala")
            selectedObject = R.raw.koala_bear
        }

        lion.setOnClickListener {
            setModelName("Larry the Lion")
            selectedObject = R.raw.lion
        }

        reindeer.setOnClickListener {
            setModelName("Rudolph the Reindeer")
            selectedObject = R.raw.reindeer
        }

        wolverine.setOnClickListener {
            setModelName("Winnie the Wolverine")
            selectedObject = R.raw.wolverine
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