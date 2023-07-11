import React, { useState, useEffect, useRef } from 'react';

function useInterval(callback: any, delay: any) {
    const savedCallback  = useRef<() => void>();

    // Remember the latest callback.
    useEffect(() => {
        savedCallback.current = callback;
    }, [callback]);

    // Set up the interval.
    useEffect(() => {
        function tick() {
            if (savedCallback.current)
                savedCallback?.current();
        }
        if (delay !== null) {
            tick()
            let id = setInterval(tick, delay);
            return () => clearInterval(id);
        }
    }, [delay]);
}

export default useInterval