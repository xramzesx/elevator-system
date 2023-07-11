import constants from "src/common/constants"
import styled from "styled-components"


interface IElevatorDoor {
    isOpen: boolean
}

const ElevatorDoor = styled.div<IElevatorDoor>`
    background: ${props => props.isOpen ? "#00f4" : "#eeee"};
    width: 100%;
    max-width: ${constants.elevator.car.width};
    height: ${constants.elevator.car.height};
    transition: background .25s ease;
`

export default ElevatorDoor