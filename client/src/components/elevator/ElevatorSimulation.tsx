import { ElevatorStatus } from "../../common/types";
import { useEffect, useMemo, useState } from "react";
import ElevatorFloor from "./floor/ElevatorFloor";
import { styled } from "styled-components";
import useInterval from "src/hooks/useInterval";
import { getRandomInt } from "src/common/utils";
import ElevatorCarManager from "./car/ElevatorCarManager";


const Container = styled.div`
    ;
`



interface IElevatorSimulation {
    // lowestFloor: number;
    // highestFloor: number;
    simulationId?: number;
}

const ElevatorSimulation = (props : IElevatorSimulation) => {
    const [lowestFloor, setLowestFloor] = useState(-10)
    const [highestFloor, setHighestFloor] = useState(10)
    
    const [statusList, setStatusList] = useState<ElevatorStatus[]>([
        {elevatorId: 0, current: 10, destination: 10, isOpen: true},
        {elevatorId: 1, current: 10, destination: 10, isOpen: false},
        {elevatorId: 2, current: 10, destination: 10, isOpen: false},
        {elevatorId: 3, current: 10, destination: 10, isOpen: false},
        {elevatorId: 4, current: 10, destination: 10, isOpen: false},
        {elevatorId: 5, current: 10, destination: 10, isOpen: false},
        {elevatorId: 6, current: 10, destination: 10, isOpen: false},
        {elevatorId: 7, current: 10, destination: 10, isOpen: false},
        {elevatorId: 8, current: 10, destination: 10, isOpen: false},
        {elevatorId: 9, current: 10, destination: 10, isOpen: false},
        {elevatorId: 10, current: 10, destination: 10, isOpen: false},
        {elevatorId: 11, current: 10, destination: 10, isOpen: false},
        {elevatorId: 12, current: 10, destination: 10, isOpen: false},
        {elevatorId: 13, current: 10, destination: 10, isOpen: false},
        {elevatorId: 14, current: 10, destination: 10, isOpen: false},
        {elevatorId: 15, current: 10, destination: 10, isOpen: false},
    ])

    useInterval(() => {
        setStatusList(statusList.map((status, index) => ({
            ...status, 
            current: getRandomInt(lowestFloor, highestFloor),
            destination: getRandomInt(lowestFloor, highestFloor),
            isOpen: !!getRandomInt(0,1)
        })))
    }, 5000)

    useEffect(() => {

    },[props.simulationId])

    const floors = useMemo(
        () => {
            const result = []

            for (let i = highestFloor; i >= lowestFloor; i--) {
                result.push((<
                    ElevatorFloor 
                    floor={i} 
                    statusList={statusList}
                    isHighest={i == highestFloor}
                    isLowest={i == lowestFloor}
                    key={i}
                />))
            }

            return result
        }, [lowestFloor, highestFloor, statusList]
    )

    return (
        <Container>
            <ElevatorCarManager 
                statusList={statusList} 
                lowestFloor={lowestFloor} 
                highestFloor={highestFloor}                
            />
            {floors}
        </Container>
    )
}

export default ElevatorSimulation