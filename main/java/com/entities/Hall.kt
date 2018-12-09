package com.entities

import java.io.Serializable
import javax.persistence.*

@Entity
@Table
class Hall : Serializable {

    @Basic
    @Column(name = "capacity", nullable = false)
    private var _capacity: Int?

    constructor(capacity: Int){
        _capacity = capacity
    }

    constructor(){
        _capacity = null
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_id", nullable = false)
    private var id: Long? = null

    @OneToMany(mappedBy = "hall")
    private lateinit var sessions: MutableList<Session>

    val capacity get() = _capacity
}