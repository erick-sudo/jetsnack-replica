import os
import requests

image_urls = [
    "https://source.unsplash.com/pGM4sjt_BdQ",
    "https://source.unsplash.com/Yc5sL-ejk6U",
    "https://source.unsplash.com/-LojFX9NfPY",
    "https://source.unsplash.com/3U2V5WqK1PQ",
    "https://source.unsplash.com/Y4YR9OjdIMk",
    "https://source.unsplash.com/bELvIg_KZGU",
    "https://source.unsplash.com/YgYJsFDd4AU",
    "https://source.unsplash.com/0u_vbeOkMpk",
    "https://source.unsplash.com/yb16pT5F_jE",
    "https://source.unsplash.com/AHF_ZktTL6Q",
    "https://source.unsplash.com/rqFm0IgMVYY",
    "https://source.unsplash.com/qRE_OpbVPR8",
    "https://source.unsplash.com/33fWPnyN6tU",
    "https://source.unsplash.com/aX_ljOOyWJY",
    "https://source.unsplash.com/UsSdMZ78Q3E",
    "https://source.unsplash.com/7meCnGCJ5Ms",
    "https://source.unsplash.com/m741tj4Cz7M",
    "https://source.unsplash.com/iuwMdNq0-s4",
    "https://source.unsplash.com/qgWWQU1SzqM",
    "https://source.unsplash.com/9MzCd76xLGk",
    "https://source.unsplash.com/1d9xXWMtQzQ",
    "https://source.unsplash.com/wZxpOw84QTU",
    "https://source.unsplash.com/okzeRxm_GPo",
    "https://source.unsplash.com/l7imGdupuhU",
    "https://source.unsplash.com/bkXzABDt08Q",
    "https://source.unsplash.com/y2MeW00BdBo",
    "https://source.unsplash.com/1oMGgHn-M8k",
    "https://source.unsplash.com/TIGDsyy0TK4"
]

dir_name = "images/snacks"

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