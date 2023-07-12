const api = {
    /// TODO: use environment variables ///
    url : new URL("http://localhost:8080")
}

const ui =  {
    maxWidth: "1200px",
    mobileWidth: "800px",
    colors : {
        background: "hsl(246deg 100% 25%)",
        foreground: "hsl(246deg 40% 90%)"
    }
}

const elevatorCar = {
    height: 5,
    width: 3
}

const elevator = {
    car: {
        height: elevatorCar.height + "rem",
        width: elevatorCar.width + "rem",
        intHeight: elevatorCar.height,
        intWidth: elevatorCar.width
    },
    panel : {
        width: elevatorCar.width + "rem"
    },
    count : {
        max: 16,
        min: 1
    }
}

export default {ui, elevator, api}