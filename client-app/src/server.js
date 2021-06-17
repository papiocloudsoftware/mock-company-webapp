import { createServer } from "miragejs"

export default function () {
  createServer({
    routes() {
      this.get("/api/movies", () => ({
        movies: [
            { id: 1, name: "Inception", year: 2010 },
            { id: 2, name: "Interstellar", year: 2014 },
            { id: 3, name: "Dunkirk", year: 2017 },
            { id: 4, name: "Crazy About her", year: 2021 },
            { id: 5, name: "Mank", year: 2020 },
            { id: 6, name: "The King", year: 2019 },
            { id: 7, name: "The Paramedic", year: 2020 },
            { id: 8, name: "The Kissing Booth", year: 2018 },
            { id: 9, name: "To all The Boys Always..", year: 2021 },
            { id: 10, name: "Project Power", year: 2020 }
          ],
      }))
    }
  })
}
