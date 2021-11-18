import { createServer } from "miragejs"

import image1 from "./images/1.png"
import image2 from "./images/2.png"
import image3 from "./images/3.png"
import image4 from "./images/4.png"
import image5 from "./images/5.png"
import image6 from "./images/6.png"
import image7 from "./images/7.png"
import image8 from "./images/8.png"
import image9 from "./images/9.png"
import image10 from "./images/10.png"

const productList = [
  { id: 1, description: "Stuff", cost: 201.0, image: image1 },
  { id: 2, description: "Beautiful thing", cost: 2014, image: image2 },
  { id: 3, description: "Lovely", cost: 20.17, image: image3 },
  { id: 4, description: "Check this out", cost: 2.021, image: image4 },
  { id: 5, description: "Interesting!", cost: 20.20, image: image5 },
  { id: 6, description: "Running out of ideas", cost: 20.19, image: image6 },
  { id: 7, description: "The Bestest", cost: 2.020, image: image7 },
  { id: 8, description: "One of a Kind", cost: 201.8, image: image8 },
  { id: 9, description: "Wonderful", cost: 2.021, image: image9 },
  { id: 10, description: "Great Scott!", cost: 20.20, image: image10 }
]

export function makeServer() {
  createServer({
    routes() {
      this.get("/api/products/list", () => productList)
      
      this.get("/api/products/search", (schema, request) => {
        let searchedName = request.queryParams.query;
        const productsContainingSearch = [];
        productList.forEach((p) => {
          if (p.description.toLowerCase().includes(searchedName.toLowerCase()) ||
          p.cost === +searchedName || p.id === +searchedName) {
            productsContainingSearch.push(p);
          }
        })
        return productsContainingSearch
      })
    }
  })
}
