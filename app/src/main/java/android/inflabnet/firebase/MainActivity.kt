package android.inflabnet.firebase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    //conexão
    private lateinit var firebasestorage: FirebaseStorage
    //referência inicial apontando o diretório raiz
    lateinit var storageReference: StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebasestorage = FirebaseStorage.getInstance()
        //duretório raiz abrindo conexão e pegando as referências
        storageReference = firebasestorage.reference
        val logo = storageReference.child("testeUpload.png")
        //val chat = storageReference.child("Foto/newchatbg.PNG")

        //uploads
        //converter o arquivo de imagem em bitmap para upload
        val imagem  = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.addlisttwo)
        val baos = ByteArrayOutputStream() //array de bytes

        //comprime
        imagem.compress(Bitmap.CompressFormat.PNG,100,baos)//a compressão ocorre no baos


        //upload
        val uploadTask = logo.putBytes(baos.toByteArray()) //retorna UploadTask
        uploadTask
                .addOnSuccessListener{
                    Toast.makeText(this,"Upload ok!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }

        //logo.putFile()
        //logo.putStream()

    }
}
