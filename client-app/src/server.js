import { createServer } from "miragejs"

const productList = [
  { id: 1, description: "Stuff", cost: 201.0, image: "../images/1.png" },
  { id: 2, description: "Beautiful thing", cost: 2014, image: "../images/2.png" },
  { id: 3, description: "Lovely", cost: 20.17, image: "../images/3.png" },
  { id: 4, description: "Check this out", cost: 2.021, image: "../images/4.png" },
  { id: 5, description: "Interesting!", cost: 20.20, image: "../images/5.png" },
  { id: 6, description: "Running out of ideas", cost: 20.19, image: "../images/6.png" },
  { id: 7, description: "The Bestest", cost: 2.020, image: "../images/7.png" },
  { id: 8, description: "One of a Kind", cost: 201.8, image: "../images/8.png" },
  { id: 9, description: "Wonderful", cost: 2.021, image: "../images/9.png" },
  { id: 10, description: "Great Scott!", cost: 20.20, image: "../images/10.png" }
]

export default function () {
  createServer({
    routes() {
      this.get("/api/products/list", () => ({
        products: productList,
      }))
      
      this.post("/api/products/search", (schema, request) => {
        let searchedName = request.queryParams.query;
        const productsContainingSearch = [];
        productList.forEach((p) => {
          if (p.description.toLowerCase().includes(searchedName.toLowerCase()) ||
          p.cost === +searchedName || p.id === +searchedName) {
            productsContainingSearch.push(p);
          }
        })
        return { productsSearched: productsContainingSearch }
      })
    }
  })
}
