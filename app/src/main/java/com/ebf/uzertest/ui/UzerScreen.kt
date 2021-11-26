package com.ebf.uzertest.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebf.uzertest.R
import com.ebf.uzertest.ui.theme.UzerTestTheme

/**
 * Il s'agit de l'écran final avec les deux listes horizontales
 */
@Composable
fun UzerScreen() {
     // Scaffold ne nous sert pas beaucoup dans notre cas actuel mais peut rapidement
     // devenir très utile lorsque l'écran se complexifie
     // Voir plus ici : shorturl.at/rGMOS
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            // LazyColumn nous permet d'afficher des listes de manière optimisé
            // comme avec les recyclerview
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(all = 10.dp)
            ) {

                // Dans notre cas, on affiche 2 sections
                items(count = 2) {
                    UzerSection()
                }

            }
        }
    }
}

/**
 * Affiche une série de cartes horizontallement
 * avec un titre
 *
 * @param title Le titre de la section
 */
@Composable
fun UzerSection(title: String = "Titre") {
    Card(
        elevation = 4.dp,
        modifier = Modifier.padding(all = 2.dp)
    ) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            // Je me suis permis d'ajouter un titre,
            // mais on peut retirer ça facilement
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(count = 5) {
                    UzerCard(text = "Card n°$it")
                }
            }
        }
    }
}

/**
 * Carte avec une image et en dessous un texte aligné horizontalement
 *
 * @param imageDrawable Le drawable de l'image à afficher
 * @param text Le texte à afficher
 */
@Composable
fun UzerCard(
    @DrawableRes imageDrawable: Int = R.drawable.unsplash,
    text: String = "Un texte"
) {
    Card(elevation = 4.dp) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(all = 10.dp)
        ) {
            Image(
                painter = painterResource(id = imageDrawable),
                contentScale = ContentScale.Crop,
                contentDescription = "Image",
                modifier = Modifier
                    .size(width = 150.dp, height = 200.dp)
                    .clip(MaterialTheme.shapes.small)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = text)
        }
    }
}

/**
 * PREVIEWS
 *
 * Les Previews sont des composable qui servent uniquement au développeur
 * Elles permettent de prévisualiser directement dans Android Studio les composables
 * et donc de développer plus rapidement.
 *
 * Pour voir les previews, il suffit de cliquer sur "Split" ou "Design"
 * en haut à droite de cette fenetre.
 * Pour que cela fonctionne, il faut avoir build le projet.
 *
 * En savoir plus : https://developer.android.com/jetpack/compose/tooling
 */

@Preview
@Composable
fun UzerCardPreview() {
    UzerTestTheme {
        UzerCard()
    }
}

@Preview
@Composable
fun UzerSectionPreview() {
    UzerTestTheme {
        UzerSection()
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)    // preview avec le dark mode
@Composable
fun UzerScreenPreview() {
    UzerTestTheme {
        UzerScreen()
    }
}
