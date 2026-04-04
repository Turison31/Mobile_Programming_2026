package ug.ac.ndejje.welcome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ug.ac.ndejje.welcome.ui.theme.NdejjeWelcomeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NdejjeWelcomeAppTheme {
                StudentDirectory()

            }
        }
    }
}
@Composable
fun StudentInfo(student: Student ) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(all = 16.dp),
    ){
        Image(
            painterResource(student.profileImageId),
            contentDescription = "profile picture",
            modifier = Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(percent = 50))
                .padding(bottom = 8.dp),
            contentScale = ContentScale.Crop
        )
        Text (text = student.name,
            style = MaterialTheme.typography.headlineSmall
            )
        Text(
            text= student.regNumber,
            color = Color.Gray
        )
    }

}
@Composable
fun StudentIdCard( student: Student)

{
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
            shape = RoundedCornerShape(size = 16.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)

            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally )
        {
        StudentInfo(student)
        Button(onClick = { } ) {
            Text("Mark Attendance") }
    }
}
}
@Composable
fun StudentDirectory(){
    val students = StudentProvider.studentList
    LazyColumn (
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues( all = 16.dp))
    {
        items ( students ) { student ->
            StudentIdCard(student = student)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
    )
@Composable
fun GreetingPreview() {
    NdejjeWelcomeAppTheme {
        StudentDirectory()

    }
}


