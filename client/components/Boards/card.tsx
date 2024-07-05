import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea, CardActions } from '@mui/material';
import axios from 'axios'

const cardImageHeight = 140
const cardMaxWidth = 345

export default function BoardCard({ title, description, actionButton }: {
  title: string,
  description: string,
  actionButton: React.JSX.Element | undefined
}) {
  const [imageURL, setImageURL] = React.useState<any>()
  React.useEffect(() => {
    getRandomImage().then(image => {
      setImageURL(URL.createObjectURL(image))
    })
  }, [])
  return (
    <Card sx={{ maxWidth: cardMaxWidth }}>
      <CardActionArea>
        <CardMedia
          component="img"
          height={cardImageHeight}
          image={imageURL}
          alt="random image"
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            {title}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            {description}
          </Typography>
        </CardContent>
      </CardActionArea>
      {actionButton &&
        <CardActions>
          {actionButton}
        </CardActions>
      }
    </Card>
  )
  async function getRandomImage(): Promise<any | undefined> {
    try {
      const { data: imageBlob } = await axios.get(`https://picsum.photos/${cardMaxWidth}/${cardImageHeight}`, { responseType: 'blob' })
      return imageBlob
    } catch (e) { }
  }
}
