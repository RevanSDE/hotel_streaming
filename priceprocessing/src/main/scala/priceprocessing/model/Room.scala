package priceprocessing.model

case class Room(hotel_name: String,
                room_name: String,
                price: Option[Double],
                status: String,
                from_date: String,
                to_date: String)