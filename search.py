import os
import requests

image_urls = [
    "https://source.unsplash.com/UsSdMZ78Q3E",
    "https://source.unsplash.com/SfP1PtM9Qa8",
    "https://source.unsplash.com/_jk8KIyN_uA",
    "https://source.unsplash.com/UsSdMZ78Q3E",
    "https://source.unsplash.com/7meCnGCJ5Ms",
    "https://source.unsplash.com/m741tj4Cz7M",
    "https://source.unsplash.com/dt5-8tThZKg",
    "https://source.unsplash.com/ReXxkS1m1H0",
    "https://source.unsplash.com/IGfIGP5ONV0",
    "https://source.unsplash.com/9MzCd76xLGk"
]

dir_name = "images/search"

# Create a directory to save the images
os.makedirs(dir_name, exist_ok=True)

# Function to download an image from a URL
def download_image(url):
    try:
        response = requests.get(url)
        response.raise_for_status()  # Check if the request was successful
        filename = os.path.join(dir_name, os.path.basename(url.split('?')[0]))
        with open(filename, 'wb') as f:
            f.write(response.content)
        print(f"Downloaded {filename}")
    except requests.exceptions.RequestException as e:
        print(f"Failed to download {url}: {e}")

# Download all images
for url in image_urls:
    download_image(url)