package priceprocessing.model

case class Room(hotelName: String,
                roomName: String,
                price: Option[Double],
                status: String,
                fromDate: String,
                toDate: String)