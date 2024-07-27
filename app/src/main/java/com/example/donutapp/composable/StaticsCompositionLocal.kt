import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class Language {
    English,
    Spanish,
    French
}

private val LocalCurrentLanguage = staticCompositionLocalOf<Language> {
    Language.English
}

@Composable
fun ProvideLanguage(language: Language, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalCurrentLanguage provides language) {
        content()
    }
}

@Composable
fun CurrentLanguage(): Language {
    return LocalCurrentLanguage.current
}

@Composable
fun LanguageExample() {
    var currentLanguage by remember { mutableStateOf(Language.English) }

    Column {
        ProvideLanguage(currentLanguage) {
            Text("Current Language: $currentLanguage")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            ChangeLanguageButton(
                text = "English",
                language = Language.English,
                currentLanguage = currentLanguage,
                updateLanguage = { currentLanguage = it }
            )
            ChangeLanguageButton(
                text = "Spanish",
                language = Language.Spanish,
                currentLanguage = currentLanguage,
                updateLanguage = { currentLanguage = it }
            )
            ChangeLanguageButton(
                text = "French",
                language = Language.French,
                currentLanguage = currentLanguage,
                updateLanguage = { currentLanguage = it }
            )
        }
    }
}

@Composable
fun ChangeLanguageButton(
    text: String,
    language: Language,
    currentLanguage: Language,
    updateLanguage: (Language) -> Unit
) {
    Button(
        onClick = { updateLanguage(language) },
        enabled = language != currentLanguage
    ) {
        Text(text = text)
    }
}

@Preview(showSystemUi = true)
@Composable
fun LanguageExamplePreview() {

        Surface {
            LanguageExample()
        }

}
