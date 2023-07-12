import constants from "src/common/constants";
import { ElevatorStatus } from "src/common/types"
import { styled } from "styled-components"
import ElevatorCar from "./ElevatorCar";

const ElevatorIndicator = styled.div`

`

const Container = styled.div`
    background: #0005;
    width: 100%;
    max-width: ${constants.elevator.car.width};
    height: 1rem;
    position: relative;
`

interface IElevatorCarTrack {
    status: ElevatorStatus;
    lowestFloor: number;
    highestFloor: number;
}

const ElevatorCarTrack = (props: IElevatorCarTrack) => {

    return (
        <Container>
            <ElevatorCar 
                $status={props.status}
                $lowestFloor={props.lowestFloor}
                $highestFloor={props.highestFloor}
            />
        </Container>
    )

}

export default ElevatorCarTrack