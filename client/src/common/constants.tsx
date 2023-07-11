const ui =  {
    maxWidth: "1200px",
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
    }
}

export default {ui, elevator}